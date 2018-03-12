package it.corelab.airbooks.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import it.corelab.airbooks.MySpannable;
import it.corelab.airbooks.R;
import it.corelab.airbooks.object.Review;

/**
 * Created by Roberto_Vecchio on 10/03/18.
 */

public class CustomListViewAdapter extends ArrayAdapter<Review> {
    public static final int MAX_LINES = 3;

    public CustomListViewAdapter(Context context, int textViewResourceId, List<Review> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_listview_item_all_reviews, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.image_profile_all_reviews);
        TextView textView = (TextView) convertView.findViewById(R.id.name_surname);
        final TextView description = (TextView) convertView.findViewById(R.id.expandable_text);

        ImageView stella1 = (ImageView) convertView.findViewById(R.id.stella_1);
        ImageView stella2 = (ImageView) convertView.findViewById(R.id.stella_2);
        ImageView stella3 = (ImageView) convertView.findViewById(R.id.stella_3);
        ImageView stella4 = (ImageView) convertView.findViewById(R.id.stella_4);
        ImageView stella5 = (ImageView) convertView.findViewById(R.id.stella_5);

        final Review items = getItem(position);

        image.setImageResource(items.getDrawable());
        textView.setText(items.getName());
        description.setText(items.getDescription());

        int vote = items.getVote();

        switch (vote){
            case 5:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_piena);
                stella4.setImageResource(R.drawable.stella_piena);
                stella5.setImageResource(R.drawable.stella_piena);
                break;
            case 4:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_piena);
                stella4.setImageResource(R.drawable.stella_piena);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            case 3:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_piena);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            case 2:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_piena);
                stella3.setImageResource(R.drawable.stella_vuota);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            case 1:
                stella1.setImageResource(R.drawable.stella_piena);
                stella2.setImageResource(R.drawable.stella_vuota);
                stella3.setImageResource(R.drawable.stella_vuota);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);
                break;
            default:
                stella1.setImageResource(R.drawable.stella_vuota);
                stella2.setImageResource(R.drawable.stella_vuota);
                stella3.setImageResource(R.drawable.stella_vuota);
                stella4.setImageResource(R.drawable.stella_vuota);
                stella5.setImageResource(R.drawable.stella_vuota);

        }

        makeTextViewResizable(description, 3, "See More", true);
        return convertView;
    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean onPreDraw() {

                Context context;

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeOnPreDrawListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
                return true;
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
}
